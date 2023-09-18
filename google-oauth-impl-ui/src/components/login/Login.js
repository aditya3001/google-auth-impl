const Login = ()=>{
    const handleSignIn = async(e)=>{
            e.preventDefault();
            const requestOptions = {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' ,"Access-Control-Allow-Origin": "*"},
            };
            try {
                const response = await fetch(`http://localhost:8080/getResource`,requestOptions);
                if(response.ok){
                    const data = await response.json();
                    console.log(data)
                }else{
                    
                }
              } catch (error) {
                // Handle error
              }
        }
    
    return <>
        <button onClick={handleSignIn}>Sign In with Google</button>
    </>
}

export default Login;