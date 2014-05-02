
$(
 function()
 {
  $("tr[class = 'hoverable']").hover(
   function()
   {
    $(this).addClass("highlight");
   },
   function()
   {
    $(this).removeClass("highlight");
   }
  )
 }
)
