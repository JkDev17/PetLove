const username = "Alvaro Morata"
const password = "abcdefg1234"
const country =  "USA"
const city    =  "New York"
var image
var petImage
const sex = 'FEMALE'
const petName = "Luna"
const type = "dog"
const  breed = "German Shepherd"
const age = "4"
const  isFullyVaccinated = true


function saveBreederAndPet()
{

    let url =  "http://localhost:8080/pets/savePet"
    let url2 = "http://localhost:8080/breeders/saveBreeder"

    petImage = window.localStorage.getItem("imgData2")
    image = window.localStorage.getItem("imgData")

    let petData =
        {
            name: petName,
            type: type,
            breed: breed,
            age: age,
            isFullyVaccinated: isFullyVaccinated,
            image: petImage,
            sex:sex
        }


    let fetchData =
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(petData)
        }



    fetch(url, fetchData)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            alert("First result is: " + data)


            let breederData =
                {
                    fullName: username,
                    password: password,
                    country: country,
                    city: city,
                    petId: data,
                    image: image
                }

            let fetchData2 =
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(breederData)
                }

            fetch(url2, fetchData2)
                .then((response) => {
                    return response.json();
                })
                .then((data) => {
                    alert("2nd result that i get is:" + data)

                })
                .catch((error) => {
                    alert(error)
                })
        })
        .catch((error) => {
            alert(error)})
}

$("#page4h3").hide();

function displayStuff(id)
{


    if(id === "1")
    {
        $("#page1").show();
        $("#page2").hide();
        $("#page3").hide();
        $("#page4").hide();
    }

    if(id === "2")
    {

        $("#li3").removeClass("page-item disabled")
        $("#li3").addClass("page-item")
        $("#page3").hide()
        $("#page1").hide()
        $("#page4").hide()
        $("#page2").show()
    }

    if(id === "3")
    {
        $("#page2").hide()
        $("#page1").hide()
        $("#page4").hide()
        $("#page3").show()
        alert(username)
    }

    if(id === "4")
    {

    }
}

$(function () {
    "use strict";
    $(".pCard_add").click(function () {
        $(".pCard_card").toggleClass("pCard_on")
        $(".pCard_add i").toggleClass("fa-minus")
    })
})
