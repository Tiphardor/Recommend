/**
 * Created by Anglet on 16/4/2.
 */
(function(){
    $(".course-detail").bind("mouseenter", function () {
        var that = $(this);
        that.find(".product_section1").removeClass("fadeInFromTop").addClass("fadeOutToTop");
        that.find(".product_section2").removeClass("fadeOutToBottom").addClass("fadeInFromBottom");
    });
    $(".course-detail").bind("mouseleave", function () {
        var that = $(this);
        that.find(".product_section2").removeClass("fadeInFromBottom").addClass("fadeOutToBottom");
        that.find(".product_section1").removeClass("fadeOutToTop").addClass("fadeInFromTop");
    });
}());