import React, { useState, useEffect } from "react";


function TicketDetails() {

    const [ticketdata, setTicketData] = useState([]);
    useEffect(() => {
        const dataString = localStorage.getItem('userData');
        const data = JSON.parse(dataString);
        fetch(`http://localhost:8080/tickets/{empId}?empId=${data.empId}`)
            .then(response => response.json())
            .then(data => setTicketData(data))
            .catch(error => console.error(error));
    }, []);

    async function handleCancel(formData) {
        const dataString = localStorage.getItem('userData');
        const data = JSON.parse(dataString);

        try {
            if (data.empId) {
                const response = await fetch(`http://localhost:8080/tickets/cancel?empId=${data.empId}&spotId=${ticketdata.spotId}`, {
                    method: "PUT",
                    body: formData,
                });
                const result = await response.json();
                console.log("Success:", result);
            }
        } catch (error) {
            console.error("Error:", error);
        }
        alert("Ticket cancelled!");
        window.location.reload();
    }
    // function handleOccupied(){

    // }

    return (
        <div>
            {(ticketdata.spotId) ? <div>
                <ul class="list-group">
                    <li class="list-group-item text-center"><h2 className="text-dark">Ticket Details</h2></li>
                    <li class="list-group-item fs-5">{`Ticket ID : ${ticketdata.id}`}</li>
                    <li class="list-group-item fs-5">{`Booking Date : ${ticketdata.date}`}</li>
                    <li class="list-group-item fs-5">{`Booking Time : ${ticketdata.time}`}</li>
                    <li class="list-group-item fs-5">{`Spot ID : ${ticketdata.spotId}`}</li>
                    <li class="list-group-item fs-5">{`Vehicle Number : ${ticketdata.vehicleNumber}`}</li>
                    <li class="list-group-item fs-5">{`Vehicle Type : ${ticketdata.vehicleType}`}</li>

                    <div className="d-flex justify-content-center mt-4 w-20">
                        <div className="ticket-details-button">
                            <ul className="list-group list-group-horizontal">
                                {/* {<li className="list-group-item">
                                    <button className="btn btn-secondary w-40" onClick={handleOccupied}>
                                        Cancel
                                    </button>
                                </li>
                                } */}
                                <li className="list-group-item">
                                    <button className="btn btn-danger w-20" onClick={handleCancel}>
                                        Cancel
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </ul>
            </div> : <h2 className="text text-center my-3">No Active booking!!</h2>
            }
        </div>
    )
}
export default TicketDetails;