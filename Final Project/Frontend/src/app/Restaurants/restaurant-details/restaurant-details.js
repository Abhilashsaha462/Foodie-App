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
}
