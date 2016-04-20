/**
 * Created by Anglet on 16/4/2.
 */
(function(){
    $("ul.select-ul").addClass("hide");
    $(".select-input,ul.select-ul").bind("mouseenter", function () {
        $(this).closest(".select-box").find(".select-ul").removeClass("hide");
    });
    $(".select-input,ul.select-ul").bind("mouseleave", function () {
        $(this).closest(".select-box").find(".select-ul").addClass("hide");
    });
    $("ul.select-ul li").bind("click", function () {
        var that = $(this).closest(".select-box");
        var input = that.find(".select-input");
        input[0].value = $(this)[0].innerText;
        that.find("ul.select-ul").addClass("hide");
    });
    $(".close-icon,.close-btn").bind("click",function(){
        $("#modal").addClass("hidden");
    });
    $(".import-btn").bind("click",function(){
        $("#modal").removeClass("hidden");
    });
}());
