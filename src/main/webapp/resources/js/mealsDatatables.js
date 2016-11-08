/**
 * Created by Денис on 07.11.2016.
 */

var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filter').serialize(),
        success: updateTableByData
    });
}

// <c:forEach items="${meals}" var="meal">
// <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
//     <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
//     <td>
//     <%--<fmt:parseDate value="${meal.dateTime}" pattern="y-M-dd'T'H:m" var="parsedDate"/>--%>
// <%--<fmt:formatDate value="${parsedDate}" pattern="yyyy.MM.dd HH:mm" />--%>
//     ${fn:formatDateTime(meal.dateTime)}
//     </td>
//     <td>${meal.description}</td>
//     <td>${meal.calories}</td>
//     <td><a class="btn btn-xs btn-primary"><fmt:message key="common.update"/></a></td>
//     <td><a class="btn btn-xs btn-danger" onclick="deleteRow(${meal.id})"><fmt:message key="common.delete"/></a></td>
//     </tr>
//     </c:forEach>

$(function () {
    $('#startDate, #endDate').datetimepicker({
        timepicker:false,
        format: 'Y-m-d'
    });

    $('#startTime, #endTime').datetimepicker({
        datepicker:false,
        format:'H:i'
    });

    $('#dateTime').datetimepicker({format: 'Y-m-d\\TH:i'});

    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
           data.exceed ? $(row).addClass("exceeded") : $(row).addClass("normal");
        },
        "initComplete": makeEditable

    });

});

