import React,{useState,useEffect} from "react";
import { Form, Button, Container } from 'react-bootstrap';

const dataString = localStorage.getItem('userData');
const userdata = JSON.parse(dataString);

function NewBookingEmployee() {
    var vehicleType = ""
    var vehicleNumber = ""
    const [vehicleList, setVehicleList] = useState([]);
    const [selectedVehicle, setSelectedVehicle] = useState('');

    // Fetch the list of registered vehicles from API
    useEffect(() => {
        // Code to fetch vehicle list from API 
        const fetchVehicleList = async () => {
            const empId = JSON.parse(localStorage.getItem('userData')).empId;
            const response = await fetch(`http://localhost:8080/api/vehicles/${empId}`);
            const data = await response.json();
            setVehicleList(data);
        };
        fetchVehicleList();
    }, []);
    async function fetchVehicleDetails(selectedVehicle) {
        try {
            const response = await fetch(`http://localhost:8080/api/vehicles/vehicleid/${selectedVehicle}`);
            const data = await response.json();
            vehicleType = data.vehicleType;
            vehicleNumber = data.vehicleNumber;
            // alert("data fetched successuly in fetch vehicle details")
            // console.log(data)
            // console.log("selected vehicle data", vehicleNumber, vehicleType)
        } catch (error) {
            console.log("Error fetching vehicle details:", error.message);
        }
    };

    const handleSubmit = async  (event) => {
        event.preventDefault();
        await fetchVehicleDetails(selectedVehicle);
        try{
            const response = await fetch('http://localhost:8080/tickets',{
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                  empId: userdata.empId,
                  vehicleType: vehicleType,
                  vehicleNumber: vehicleNumber,
                  spotType: userdata.type
                }),
              });
              if (response.ok) {
                alert("Ticket Booked Successfully!")
                window.location.reload();
              } else {
                alert("Request Failed!")
              }}catch(error){
        console.log(error)
    }};
    
    return (
        <div>
            <Container className="my-3">
                <h2 className="text-dark my-3">Reservation Form</h2>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="formVehicleType">
                        <Form.Label className="text my-2">Select vehicle</Form.Label>
                        <Form.Control
                            as="select"
                            value={selectedVehicle}
                            onChange={(event) => {
                                setSelectedVehicle(event.target.value);
                            }}
                        >
                            <option value="">Select vehicle</option>
                            {vehicleList.map((vehicle) => (
                                <option key={vehicle.id} value={vehicle.id}>
                                    {vehicle.vehicleNumber} ({vehicle.vehicleType})
                                </option>
                            ))}
                        </Form.Control>
                    </Form.Group>
                    <br />
                    <Button id="book-button" variant="primary" type="submit">
                        Book
                    </Button>
                </Form>
            </Container>
        </div>

    )
}

export default NewBookingEmployee;