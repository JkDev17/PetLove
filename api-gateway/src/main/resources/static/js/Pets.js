var currentProfileIndex=0;
var currentProfileImgIndex=0;
var image = []
var arrOfProfileData
var size

async function loadData()
{
    await fetch("http://localhost:8080/api/gateway/collectData", {method: 'POST'})
        .then( res  =>  res.json() )
        .then( data =>
        {
            arrOfProfileData = data
        })

    if(arrOfProfileData[0] === undefined)
    {
        $(".pCard_back").hide()
        $(".pCard_down").hide()
        $(".pCard_card").hide()
        $("#errorText").show()
    }

    size= arrOfProfileData.length-1
    //$('#helloLink').html("Hello " + arrOfProfileData.email)
    $('.pCard_up').css("background-image", "url("  + arrOfProfileData[0].petsDto.image +  ")")
    $('.pCard_up').css('background-size', 'cover')
    //$('#nameUsr').html(arrOfProfileData.fullnames[currentProfileImgIndex])
    $('#locationUsr').html(arrOfProfileData[0].breedersDataDto.country + ", " + arrOfProfileData[0].breedersDataDto.city)
    $('#ageUsr').html(arrOfProfileData[0].petsDto.age)
    $('#type').html("Type:" + arrOfProfileData[0].petsDto.type)
    $('#breed').html("breed: "+arrOfProfileData[0].petsDto.breed)
    $('#isFullyVaccinated').html("Fully vaccinated: "+arrOfProfileData[0].petsDto.fullyVaccinated)
    //$('#breederName').html("Breeders name: "+arrOfProfileData[0].breedersDataDto.fullName)
    $('#breederName a').attr("href", `http://localhost:8080/api/gateway/saveWebSession?fullName=${arrOfProfileData[0].breedersDataDto.fullName}`)
    $("#matchesFound").html(arrOfProfileData[0].petsDto.name +" " + arrOfProfileData[0].petsDto.sex.toLowerCase())

}

function nextProfile()
{

    if(currentProfileIndex==size)
    {
        alert("End of profiles")
        return;
    }
    currentProfileIndex++
    currentProfileImgIndex++

    $('.pCard_up').css("background-image", "url("  + arrOfProfileData[currentProfileIndex].petsDto.image +  ")")
    $('.pCard_up').css('background-size', 'cover')
    //$('#nameUsr').html(arrOfProfileData.fullnames[currentProfileImgIndex])
    $('#locationUsr').html(arrOfProfileData[currentProfileIndex].breedersDataDto.country + ", " + arrOfProfileData[currentProfileIndex].breedersDataDto.city)
    $('#ageUsr').html(arrOfProfileData[currentProfileIndex].petsDto.age)
    $('#type').html("Type:" + arrOfProfileData[currentProfileIndex].petsDto.type)
    $('#breed').html("breed: "+arrOfProfileData[currentProfileIndex].petsDto.breed)
    $('#isFullyVaccinated').html("Fully vaccinated: "+arrOfProfileData[currentProfileIndex].petsDto.fullyVaccinated)
    $('#breederName a').attr("href", `http://localhost:8080/api/gateway/saveWebSession?fullName=${arrOfProfileData[currentProfileIndex].breedersDataDto.fullName}`)
    //$('#usersHasVisited').append('<li><a href="http://localhost:8080/VisitProfile?fullname=' +data.arrayListOfFullnames[i]+'">'+ " " +data.arrayListOfFullnames[i]+'</a></li>')
    $("#matchesFound").html(arrOfProfileData[currentProfileIndex].petsDto.name +" " + arrOfProfileData[currentProfileIndex].petsDto.sex.toLowerCase())

}

function previousProfile()
{
    if(currentProfileIndex == 0)
    {
        alert("No previous profile from the first one")
        return;
    }
    currentProfileIndex--
    currentProfileImgIndex--

    $('.pCard_up').css("background-image", "url("  + arrOfProfileData[currentProfileIndex].petsDto.image +  ")")
    $('.pCard_up').css('background-size', 'cover')
    //$('#nameUsr').html(arrOfProfileData.fullnames[currentProfileImgIndex])
    $('#locationUsr').html(arrOfProfileData[currentProfileIndex].breedersDataDto.country + ", " + arrOfProfileData[currentProfileIndex].breedersDataDto.city)
    $('#ageUsr').html(arrOfProfileData[currentProfileIndex].petsDto.age)
    $('#type').html("Type:" + arrOfProfileData[currentProfileIndex].petsDto.type)
    $('#breed').html("breed: "+arrOfProfileData[currentProfileIndex].petsDto.breed)
    $('#isFullyVaccinated').html("Fully vaccinated: "+arrOfProfileData[currentProfileIndex].petsDto.fullyVaccinated)
    $('#breederName a').attr("href", `http://localhost:8080/api/gateway/saveWebSession?fullName=${arrOfProfileData[currentProfileIndex].breedersDataDto.fullName}`)
    //$('#usersHasVisited').append('<li><a href="http://localhost:8080/VisitProfile?fullname=' +data.arrayListOfFullnames[i]+'">'+ " " +data.arrayListOfFullnames[i]+'</a></li>')
    $("#matchesFound").html(arrOfProfileData[currentProfileIndex].petsDto.name +" " + arrOfProfileData[currentProfileIndex].petsDto.sex.toLowerCase())
}