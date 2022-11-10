
const search=()=>{
    console.log("I am Searching...!!!");
    let q = $("#search-input").val();
    let query = q.charAt(0).toUpperCase().concat(q.slice(1));
    
    if(query==''){
        $(".search-result").hide();
    }else{
        console.log(query);
        let url = `http://localhost:9000/api/v2/search/${query}`;
        fetch(url).then((response)=>{
            return response.json();
        }).then((data)=>{
            console.log(data);
            let text = `<div class='list-group'>`;
            data.forEach(element => {
                text+=`<a href='/display' class='list-group-item list-group-action'> ${element.restName} </a><br>`
            });
            text+=`</div>`;
            $(".search-result").html(text);
            $(".search-result").show();
        })
      
    }
    const paymentStart=()=>{
        console.log("Payment Started...!!!");
        let amount = $("#payment_field").val();
        console.log(amount);
        if(amount==''|| amount == null){
            // alert("Amount is Required...!!!");
            swal("Failed...!!!", "Amount is Required...!!!", "error");
            return;
        }
        url:'http://localhost:9000/order-services/create_order';
    }
}
window.fbAsyncInit = function() {
    FB.init({
      appId      : '527418272190193',
      cookie     : true,
      xfbml      : true,
      version    : 'v2.3'
    });
      
    FB.AppEvents.logPageView();   
      
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));

   FBlogin = function(){
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
        console.log(response);
    });
    
    function checkLoginState() {
        FB.getLoginStatus(function(response) {
          statusChangeCallback(response);
          console.log(response);
        });
      }
    FB.login(function(response){
        if(response.authResponse){
            console.log('token',response.authResponse.accessToken);
            localStorage.setItem("Token",response.authResponse.accessToken);
            swal("Welcome", "You Have Logged in Successfully..", "success")
            window.location.href="/restaurants-details"
            FB.api('/me', function(response){
                console.log('name',response.name);
            })
        }
    },{scope: 'email,user_likes'});
   }

