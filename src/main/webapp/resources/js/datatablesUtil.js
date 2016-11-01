function makeEditable() {
    $('.delete').click(function () {
        deleteRow($(this).closest('tr').attr('id'));
    });

    $('.filter').click(function () {
        doFilter();
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $('.userEnabled').change(function () {
        $.post(ajaxUrl+"enabled",
            {"id": $(this).closest('tr').attr('id'),
             "enabled": $(this).is(":checked")});
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function add() {
    $('#id').val(null);
    $('#editRow').modal();
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}
function updateTable() {
    if (ajaxUrl === "ajax/profile/meals/")  doFilter();
    else $.get(ajaxUrl, drawTable);
}
function doFilter() {
    var form = $('#filter');
    $.post(ajaxUrl+"filter",
        form.serialize(),
        drawTable);
}

function drawTable(data) {
    datatableApi.fnClearTable();
    $.each(data, function (key, item) {
        datatableApi.fnAddData(item);

    });
    datatableApi.fnDraw();
}

function save() {
    var form = $('#detailsForm');
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
