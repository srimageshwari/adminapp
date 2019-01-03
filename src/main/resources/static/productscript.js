/**
 * Created by smuthuvel on 11/16/2018.
 */
/**
 * Created by smuthuvel on 11/16/2018.
 */

$(document).ready(function() {

    var displayResources = $("#display-resources");


    $.ajax({
        type: "GET",
        url: "http://localhost:8080/listproduct",
        success: function(result) {
            console.log(result);
            var output =
                "<table><thead><tr><th style='padding-right: 65px;'>Name</th><th>Points</th><th>Userid</th><th>Type</th></thead><tbody>";
            for (var i in result) {
                output +=
                    "<tr><td>" +
                    result[i].name +
                    "</td><td>" +
                    result[i].points +
                    "</td><td>" +
                    result[i].userId +
                    "</td><td>" +
                    result[i].type +
                    "</td></tr>";
            }
            output += "</tbody></table>";

            displayResources.html(output);
            //$("table").addClass("table");
        }
    });

});