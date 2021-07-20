import React from 'react';
import CompanyLogo from './bancoestatal.jpg';
import "./Home.css";
function Home() {
 

  return (
    <div>
        <center>
            <img src={CompanyLogo} display="block" width="800" height="500" margin="100px" />
        </center>
    </div>
  );
}
 
export default Home;