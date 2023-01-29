var currentProfileIndex=0;
var currentProfileImgIndex=0;
var image = []
var arrOfProfileData
var size

async function loadData()
{
    await fetch("http://localhost:8080/breeders/getAllBreeders", {method: 'POST'})
        .then( res  =>  res.json() )
        .then( data =>
        {
            arrOfProfileData = data
        })

    if(arrOfProfileData.images[0] === undefined)
    {
        $(".pCard_back").hide()
        $(".pCard_down").hide()
        $(".pCard_card").hide()
        $("#errorText").show()
    }

    size= arrOfProfileData.images.length-1

    $('.pCard_up').css("background-image", "url("  + arrOfProfileData.images[0] +  ")")
    $('.pCard_up').css('background-size', 'cover')
    $("#matchesFound").html("Breeder's name "+ arrOfProfileData.fullNames[0])
    $("#locationUsr").html( arrOfProfileData.cities[0]+ ", " + arrOfProfileData.countries[0])


    let url = "http://localhost:8080/api/gateway/getRatings?fullName=" + arrOfProfileData.fullNames[0]
    fetch(url)
        .then(res => res.text())
        .then(data =>
        {
            $("#ratingScore").html("Breeder Rating: "+ data + "/5")
        })

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

    $('.pCard_up').css("background-image", "url("  + arrOfProfileData.images[currentProfileIndex] +  ")")
    $('.pCard_up').css('background-size', 'cover')
    $("#matchesFound").html("Breeder's name "+ arrOfProfileData.fullNames[currentProfileIndex])
    $("#locationUsr").html( arrOfProfileData.cities[currentProfileIndex]+ ", " + arrOfProfileData.countries[currentProfileIndex])

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

    $('.pCard_up').css("background-image", "url("  + arrOfProfileData.images[currentProfileIndex] +  ")")
    $('.pCard_up').css('background-size', 'cover')
    $("#matchesFound").html("Breeder's name "+ arrOfProfileData.fullNames[currentProfileIndex])
    $("#locationUsr").html( arrOfProfileData.cities[currentProfileIndex]+ ", " + arrOfProfileData.countries[currentProfileIndex])
}