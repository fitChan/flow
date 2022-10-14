// var fixesList = {};
//
// $(document).ready(function fixesStart() {
//     $.ajax({
//         type: "GET",
//         url: "/fixes/find-true",
//         data: {},
//         success: function (response) {
//             console.log(response)
//             fixesList :response.data;
//             console.log(fixesList);
//         }
//     })
// })

$(document).ready(fetch("/fixes/find-true")
    .then(res =>{
        console.log(res)
    })
    .catch(error =>{
        console.log(error)
}))

