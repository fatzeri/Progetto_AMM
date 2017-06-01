//crea un elemento div con classe user che rappresenta un utente
function createElement(user){
    //var name = $("<h4>").html(user.nome, user.cognome);
    var link = $("<a>")
            .attr("href", "Bacheca?user="+user.id)
            .html(user.nome + " " + user.cognome); 
            
    
    var userData = $("<div>")
            .attr("class","userData")
            .append(link);
 
    return $("<div>")
            .attr("class","user")
            .append(userData);
}

function stateSuccess(data){
    var userListPage = $("#utenti");
    
    $(userListPage).empty();
    for(var instance in data){
        $(userListPage).append(createElement(data[instance]));
    }
}
function stateFailure(data, state){
    console.log(state);
}

$(document).ready(function(){
    $("#search").click(function(){
        
        var wantedUser = $("#cerca")[0].value;
        
        $.ajax({
            url: "Filter",
            data:{
                cmd:"search",
                nomeUserCercato: wantedUser
            },
            dataType:"json",
            success: function(data, state){
                stateSuccess(data)
            },
            error: function(data, state){
                stateFailure(data, state)
            }
        });
    })
});
