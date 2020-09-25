//Создание новости
function createNews() {
    $.ajax({
        type: "POST",
        url: "/create-news",
        enctype: 'multipart/form-data',
        data: new FormData($('#create-form')[0]),
        processData: false,
        contentType: false,
        cache: false,

        success: function () {
            listNews();
        }
    })
}

//Открыть новость
function openNews(id) {
    $.ajax({
        type: "GET",
        url: "/news/" + id,
        success: function (result) {
            $("#content").empty();
            let img = result.sourceImage === null ? "" : "<img src='/img/" + result.sourceImage + "'>";
            let view = "<div class='jumbotron'>" +
                "  <h1 class='display-4'>" + result.title + "</h1>" +
                "  <hr class='my-4'>" +
                "  <p>" + result.description + "</p>\n" +
                "  <p class='lead'>" +
                img +
                "  </p>" +
                "</div><p>Comments</p><div class='jumbotron' id='comment-div'></div>";
            $("#content").append(view);
            $.each(result.comments, function (i, comment) {
                let comentView =
                "<hr class='my-4'>" +
                "<li>"+comment.name+"</li><p>"+comment.description+"</p>";
                $("#comment-div").append(comentView);
            });

            $("#comment-div").append("<hr class='my-4'><form><input type='text' id='comment-name'><input id='comment-description'> <button type='button' onclick='submitComment(" + id + ")'>Submit</button></form>");

        }
    })
}

//Отправка комента
function submitComment(id) {
    let data = {
        name: $("#comment-name").val(),
        description: $("#comment-description").val()
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/send-comment/" + id,
        data: JSON.stringify(data),
        dataType: "json",
        success: function() {
            console.log(id);
            openNews(id);
        }
    });
}

//Получение новостей
function listNews() {
    $.ajax({
        type: "GET",
        url: "/list-news",
        success: function (result) {
            $("#content").empty();
            $("#content").append("<div class='jumbotron'><div class='card-columns' id='card-content'>");
            $.each(result, function (i, news) {
                let img = news.sourceImage === null ? "" : "<img src='/img/" + news.sourceImage + "' class='card-img-top'>";
                let contentCard =
                    "<div class='card'>" +
                    img +
                    "<div class='card-body'>" +
                    "<h5 class='card-title'>" + news.title + "</h5>" +
                    "<p class='card-text'>" + news.description + "</p>" +
                    "<p class='card-text'>" + news.date + "</p>" +
                    "<button class='btn btn-secondary' onclick='openNews(" + news.id + ")'>Read</button>" +
                    "<button class='btn btn-danger' onclick='deleteNews(" + news.id + ")'>Delete</button>" +
                    "</div></div>";
                $("#card-content").append(contentCard);
            });
            $("#content").append("</div></div>");
        }
    })
}

function createForm() {
    $("#content").empty();
    let form = " <form class='form-group' id='create-form'>" +
        "                    <label for='title'>Title</label>" +
        "                    <input class='form-control' type='text' name='title' id='title'>" +
        "                    <label for='description'>Description</label>\n" +
        "                    <textarea class='form-control' type='text' name='description' id='description'></textarea>\n" +
        "                    <label for='image'>Description</label>\n" +
        "                    <input type='file' class='form-control-file' id='image' name='image'>\n" +
        "                </form>" +
        "<button type='button' class='btn btn-secondary' onclick='createNews()' id='btn-upload-modal'>Upload</button>";

    $("#content").append(form);
}

function deleteNews(id) {
    $.ajax({
        type: "DELETE",
        url: "/delete-news/" + id,
        success: function () {
            listNews();
        }
    })
}

//Получение новостей по критерию
function findNews(title) {
    $.ajax({
        type: "GET",
        url: "/search-news?find=" + title,
        success: function (result) {
            $("#content").empty();
            $("#content").append("<div class='jumbotron'><div class='card-columns' id='card-content'>");
            $.each(result, function (i, news) {
                let img = news.sourceImage === null ? "" : "<img src='/img/" + news.sourceImage + "' class='card-img-top'>";
                let contentCard =
                    "<div class='card'>" +
                    img +
                    "<div class='card-body'>" +
                    "<h5 class='card-title'>" + news.title + "</h5>" +
                    "<p class='card-text'>" + news.description + "</p>" +
                    "<p class='card-text'>" + news.date + "</p>" +
                    "<button class='btn btn-secondary' onclick='openNews(" + news.id + ")'>Read</button>" +
                    "<button class='btn btn-danger' onclick='deleteNews(" + news.id + ")'>Delete</button>" +
                    "</div></div>";
                $("#card-content").append(contentCard);
            });
            $("#content").append("</div></div>");
        }
    })
}
$(document).ready(
    function () {
        listNews();

        $("#btn-create").click(function () {
            createForm();
        })

        $("#home").click(function () {
            listNews();
        })

        $("#search-form").submit(function () {
            findNews($("#search").val());
        })
    }
)