import React, { useState, useEffect } from 'react';
import Table from 'react-bootstrap/Table';
import Pagination from 'react-bootstrap/Pagination';

function MyActiveIncidents() {
  const [incidents, setIncidents] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [incidentsPerPage, setIncidentsPerPage] = useState(5);

  const empId = JSON.parse(localStorage.getItem("userData")).empId;

  useEffect(() => {
    fetch(`http://localhost:8080/securityincidents/activeincident?empId=${empId}`)
      .then((response) => response.json())
      .then((data) => setIncidents(data))
      .catch((error) => console.error(error));
  }, [empId]);

  // Get current incidents
  const indexOfLastIncident = currentPage * incidentsPerPage;
  const indexOfFirstIncident = indexOfLastIncident - incidentsPerPage;
  const currentIncidents = incidents.slice(indexOfFirstIncident, indexOfLastIncident);

  // Change page
  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  // Generate page numbers
  const pageNumbers = [];
  for (let i = 1; i <= Math.ceil(incidents.length / incidentsPerPage); i++) {
    pageNumbers.push(i);
  }

  return (
    <>
      {(incidents)?<Table striped bordered hover>
        <thead>
          <tr>
            <th>S.No.</th>
            <th>Description</th>
            <th>Spot ID</th>
            <th>Vehicle Number</th>
            <th>Reporting Date</th>
            <th>Reporting Time</th>
            <th>Entry Time</th>
            <th>Exit Time</th>
            <th>Incident Date</th>
            <th>Incident Status</th>
            <th>Admin Comments</th>
          </tr>
        </thead>
        <tbody>
          {currentIncidents.map((incident, index) => (
            <tr key={incident.id}>
              <td>{index + 1}</td>
              <td>{incident.description}</td>
              <td>{incident.spotId}</td>
              <td>{incident.vehicleNumber}</td>
              <td>{incident.date}</td>
              <td>{incident.time}</td>
              <td>{incident.entryTime}</td>
              <td>{incident.exitTime}</td>
              <td>{incident.incidentDate}</td>
              <td>{incident.status ? 'Closed' : 'Open'}</td>
              <td>{incident.comments}</td>
            </tr>
          ))}
        </tbody>
      </Table>:"No Incident Reported!"}
      <Pagination>
        {pageNumbers.map((number) => (
          <Pagination.Item key={number} active={number === currentPage} onClick={() => paginate(number)}>
            {number}
          </Pagination.Item>
        ))}
      </Pagination>
    </>
  );
}

export default MyActiveIncidents;