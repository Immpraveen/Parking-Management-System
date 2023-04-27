import { Link, useNavigate } from 'react-router-dom';
import React from "react";
import logo from "../images/logo.jpg"

function ServiceNavbar() {
  const navigate = useNavigate();
  function handleLogout() {
    localStorage.clear();
    navigate("/");
  }
  // function handleBooking(){
  //   navigate('/services')
  // }

  return (

    <nav className="navbar navbar-expand-lg navbar-light bg-light shadow">
      <img src={logo} alt="logo" className="logo" />
      <Link className="navbar-brand mx-4" to="/"><h3>ParKing</h3></Link>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
      {/* <ul className="navbar-nav"> 
          <li className="nav-item ">
            <button type="button" className="btn btn-light fs-5" onClick={handleBooking}>Booking</button>
          </li>
        </ul> */}
        <ul className='ms-auto navbar-nav px-3'>
          <li className=" nav-item ">
            <button type="button" className="btn btn-light fs-5" onClick={handleLogout}>Logout</button>
          </li>
        </ul>

      </div>
    </nav >
  );
}

export default ServiceNavbar;


