var username
var country
var city
var password

async function SaveUser() {
    username = $("#username").val()
    country = $("#country").val()
    city = $("#city").val()
    password = $("#password").val()

    if ((username === undefined || username === "") || (country === undefined || country === "") ||
        (city === undefined || city === "") || (password === undefined || password === "")) {
        $("#errorDiv").css({"display": "initial"})
        return false
    }

    if (!(document.getElementById('exampleCheck1').checked)) {
        $("#errorDiv2").css({"display": "initial"})
        return false
    }

    let url = "http://localhost:8080/users/saveUser"
    let data =
        {
            fullName: username,
            country: country,
            city: city,
            password: password
        }

    let fetchData =
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        }

    fetch(url, fetchData)
        .catch((error) => {
            alert(error)
        })

    $("#submitButton").css({"display": "none"})
    $("#successDiv").css({"display": "initial"})

    await new Promise(r => setTimeout(r, 3000))
    window.location.href = "Pets.html"
}