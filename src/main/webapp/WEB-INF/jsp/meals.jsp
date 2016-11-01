<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <div class="jumbotron">
        <div class="container">
            <div class="shadow">
                <h3><fmt:message key="meals.title"/></h3>

                <div style="margin:25px 0 25px 0;">
                    <form method="post" action="meals/filter" class="form-horizontal" id="filter">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="startDate"><fmt:message
                                    key="meals.startDate"/>:</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" name ="startDate" id="startDate" value="${param.startDate}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="endDate"><fmt:message
                                    key="meals.endDate"/>:</label>
                            <div class="col-sm-2">
                                <input type="date" class="form-control" name ="endDate" id="endDate" value="${param.endDate}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="startTime"><fmt:message
                                    key="meals.startTime"/>:</label>
                            <div class="col-sm-2">
                                <input type="time" class="form-control" name ="startTime" id="startTime" value="${param.startTime}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="endTime"><fmt:message
                                    key="meals.endTime"/>:</label>
                            <div class="col-sm-2">
                                <input type="time" class="form-control" name ="endTime" id="endTime" value="${param.endTime}">
                            </div>
                        </div>
                        <button class="btn btn-danger filter" type="button"><fmt:message key="meals.filter"/></button>
                    </form>
                </div>

                <a class="btn btn-info" onclick="add()"><fmt:message key="meals.add"/></a>

                <table class="table table-striped" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="meals.dateTime"/></th>
                        <th><fmt:message key="meals.description"/></th>
                        <th><fmt:message key="meals.calories"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach items="${meals}" var="meal">
                        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                        <tr class="${meal.exceed ? 'exceeded' : 'normal'}" id=${meal.id}>
                            <td>
                                    <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                                    <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                                    ${fn:formatDateTime(meal.dateTime)}
                            </td>
                            <td>${meal.description}</td>
                            <td>${meal.calories}</td>
                            <td class="col-md-1">
                                <button type="button" class="btn btn-primary edit"><fmt:message
                                        key="common.update"/></button>
                            </td>
                            <td class="col-md-1">
                                <button type="button" class="btn btn-danger delete"><fmt:message
                                        key="common.delete"/></button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

            </div>
        </div>
    </div>
    <div class="modal fade" id="editRow">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h2 class="modal-title"><fmt:message key="meals.add"/></h2>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" method="post" id="detailsForm">
                        <input type="text" hidden="hidden" id="id" name="id">

                        <div class="form-group">
                            <label for="dateTime" class="control-label col-xs-3"><fmt:message
                                    key="meals.dateTime"/></label>

                            <div class="col-xs-9">
                                <input type="datetime-local" class="form-control" id="dateTime" name="dateTime">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="description" class="control-label col-xs-3"><fmt:message
                                    key="meals.description"/></label>

                            <div class="col-xs-9">
                                <input type="text" class="form-control" id="description" name="description"
                                       placeholder="description">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="calories" class="control-label col-xs-3"><fmt:message
                                    key="meals.calories"/></label>

                            <div class="col-xs-9">
                                <input type="number" class="form-control" id="calories" name="calories" placeholder="">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-5"></div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-primary"><fmt:message key="common.save"/></button>
                            </div>
                            <div class="col-md-5"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">

    var ajaxUrl = 'ajax/profile/meals/';
    var datatableApi;

    // $(document).ready(function () {
    $(function () {
        datatableApi = $('#datatable').DataTable({
            "paging": false,
            "info": false,
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
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        });
        makeEditable();
    });
</script>
</html>
