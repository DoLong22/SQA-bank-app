const promise = new Promise(function (resolve) {
    setTimeout(
        function () {
            resolve(10);
        }, 2000);
})
async function handlePromise() {
    try {
        const result = await promise;
        console.log('await ' + result)
    } catch (err) {
        console.log(err)
    }
}
async function index() {
    promise.then((result) => { console.log(result) }).catch(error => console.log(error))
    await handlePromise()
    console.log('out promise')
}
index()