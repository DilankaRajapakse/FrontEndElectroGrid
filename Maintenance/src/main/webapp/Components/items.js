$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

// SAVE
$(document).on("click","#btnSave", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateItemForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // If valid
    $("#formItem").submit(); 
})

// UPDATE
//to identify the update button we didn't use an id we used a class
$(document).on("click", ".btnUpdate", function(event) 
{ 
    $("#hidcompIDSave").val($(this).closest("tr").find('#hidcompIDUpdate').val()); 
    $("#id").val($(this).closest("tr").find('td:eq(0)').text()); 
    $("#area").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#gridName").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#compType").val($(this).closest("tr").find('td:eq(3)').text()); 
    $("#complaint").val($(this).closest("tr").find('td:eq(4)').text()); 
}); 


// CLIENT-MODEL================================================================ 
function validateItemForm() { 
    // CODE 
    if ($("#id").val().trim() == "") 
    { 
        return "Insert ID."; 
    } 
    
    // NAME 
    if ($("#area").val().trim() == "") 
    { 
        return "Insert area Name."; 
    } 
    
    // PRICE------------------------------- 
    if ($("#gridName").val().trim() == "") 
    { 
        return "Insert Grid Name."; 
    } 
    
    // is numerical value 
    var tmid = $("#id").val().trim(); 
    if (!$.isNumeric(tmid)) 
    { 
        return "Insert a numerical value for ID."; 
    } 
    
   
    
    // DESCRIPTION------------------------ 
    if ($("#compType").val().trim() == "") 
    { 
        return "Insert Complaint Type."; 
    } 
    
    return true; 
} 
 