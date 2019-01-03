
$(document).ready(function() {

    var displayResources = $("#display-resources");


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/listuser",
        success: function(result) {
            console.log(result);
            var output =
                "<table><thead><tr><th>UserId</th><th>Name</th><th>Address</th><th>City</th></tr></thead><body>";
            for (var i in result) {
                output +=
                    "<tr><td>" +
                    result[i].userId +
                    "</td><td>" +
                    result[i].name +
                    "</td><td>" +
                    result[i].address +
                    "</td><td>" +
                    result[i].city +
                    "</td></tr>";
            }
            output += "</tbody></table>";

            displayResources.html(output);
            $("table").addClass("table");
        }
    });

});