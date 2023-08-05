
import React, { useState } from "react";
import { Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "./login.css"


function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
 

  let navigate=useNavigate();
  const handleLogin = async (e) => {
    e.preventDefault();
  
    try {
      const data = { email: email, password: password };
      const response = await fetch(`http://localhost:9000/kafkaapp/post`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });
  
      const responseData = await response.text(); // Parse the response as JSON
  console.log('responseData :' ,responseData)
      if (response.status === 200 && responseData === "OK") {
        console.log("Authentication Successful");
        alert("Authentication Successful");
        // Save the token to localStorage if needed
        // localStorage.setItem('authToken', token);
        navigate('/dashboard');
      } else if (responseData === "KO") {
        console.log("Authentication Failed");
        alert("Authentication Failed");
      } 
      else {
        console.log("Unknown Response:", responseData); // Print the response data
        alert("Unknown Response: " + responseData); // Show the response data in the alert
      }
    } catch (error) {
      console.error("Error occurred:", error);
    }
  };
  

  const validateEmail = () => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!re.test(email)) {
      setEmailError("Invalid email address");
    } else {
      setEmailError("");
    }
  
  };

  const validatePassword = () => {
    const re = /^(?=.*[@_$])(?=.*[a-zA-Z0-9])[a-zA-Z0-9@_$]{6,}$/;
    if (!re.test(password)) {
      setPasswordError(
        "Password must be at least 6 characters long and contain at least one special character (@, $, _)"
      );
    } else {
      setPasswordError("");
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    validateEmail();
    validatePassword();
    if (!emailError && !passwordError) {
      console.log("Email:", email);
      console.log("Password:", password);
    }
    
  };

  return (
    <div className="loginPage">
    <div className="title">LOGIN</div>
       <div className="form">
       <Form>
      <Form.Group className='formGroup'>
        <Form.Label className="headingFont">Email</Form.Label>
               
       
        <span className="asterisk">&#42;</span> 
        <Form.Control 
        
          type="email"
          
           className={`errorMessage ${emailError ? "is-invalid" : ""}`}
          value={email}
          placeholder="Enter email" required 
          
          onChange={(e) => setEmail(e.target.value)} onSubmit={handleSubmit}
          onBlur={validateEmail}
          
        />
         {/* {emailError && <span className="errorMessage"><small>{emailError}</small></span>}  */}
         <span className='errorMessage'><small>{emailError}</small></span>
        
      </Form.Group>
      

      <Form.Group className="formGroup">
        <Form.Label className="headingFont">Password</Form.Label>
      
        <span className="asterisk">&#42;</span> 
        <Form.Control
          type="password"
          className={`errorMessage ${passwordError ? "is-invalid" : ""}`}
          value={password}
          placeholder="Enter password" required
          onChange={(e) => setPassword(e.target.value)}onSubmit={handleSubmit}
          onBlur={validatePassword}
          
        />
         {passwordError && <span className="errorMessage"><small>{passwordError}</small></span>} 
      </Form.Group>
    
    
   <Form.Group style={{marginTop:'20px', marginLeft:'77px'}}>
    <button type="submit" className="btn btn-primary" 
    disabled={(!email || !password )}  onClick={handleLogin} >
                        Submit
                    </button>
      
</Form.Group >

               

    </Form>
    </div>
    </div>
  );
}

export default Login;




