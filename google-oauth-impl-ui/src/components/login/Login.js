const Login = ()=>{
    // const handleSignIn = async(e)=>{
    //         e.preventDefault();
    //         const requestOptions = {
    //             method: 'GET',
    //             headers: { 'Content-Type': 'application/json' ,"Access-Control-Allow-Origin": "*"},
    //         };
    //         try {
    //             const response = await fetch(`http://localhost:8080/oauth2/authorization/google`,requestOptions);

    //             if(response.ok){
    //                 const data = await response.json();
    //                 console.log(data)
    //             }else{
                    
    //             }
    //           } catch (error) {
    //             // Handle error
    //           }
    //     }
    const handleSignIn = (e)=>{
        e.preventDefault();
        const requestOptions = {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' ,"Access-Control-Allow-Origin": "*"},
            mode: 'no-cors'
        };
        const url = "http://localhost:8080/oauth2/authorization/google?redirect=http://localhost:8080/oauth2/callback/google"
        fetch(url,requestOptions).then(response=>{
            console.log(response.url)
            window.location.assign = response.url
        }).catch(err=>{
            console.log(err)
        });
    }
    const handleLogout = (e)=>{
        e.preventDefault();
        const requestOptions = {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' ,"Access-Control-Allow-Origin": "*"},
        };
        const url = "http://localhost:8080/logout"
        fetch(url,requestOptions).then(response=>{
            console.log(response)
        }).catch(err=>{
            console.log(err)
        });
    }
    return <>
        <button onClick={handleSignIn}>Sign In with Google</button>
        <button onClick={handleLogout}>LogOut</button>
    </>
}

export default Login;