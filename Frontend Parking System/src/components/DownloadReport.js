import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";

function DownloadReport() {
  const [quarter, setQuarter] = useState("");
  const [month, setMonth] = useState("");
  const [year, setYear] = useState("");
  const [downloadOption, setDownloadOption] = useState(""); 

  const handleQuarterlyDownload = () => {
    if (!quarter) {
      alert("Please select a quarter");
    } else {
      window.location.href = `http://localhost:8080/reports/downloadQuarterlyReport/${quarter}`;
    }
  };

  const handleMonthlyDownload = () => {
    if (!month) {
      alert("Please select a month");
    } else {
      window.location.href = `http://localhost:8080/reports/downloadMonthlyReport/${month}`;
    }
  };

  const handleYearlyDownload = () => {
    if (!year) {
      alert("Please enter a year");
    } else if (!/^[2][0-9]{3}$/.test(year)) {
      alert("Please enter a valid year in the format of 2xxx");
    } else {
      window.location.href = `http://localhost:8080/reports/downloadYearlyReport/${year}`;
    }
  };

  const handleDownloadOptionChange = (event) => {
    setDownloadOption(event.target.value);
  };

  return (
    <>
      <Form.Group controlId="formDownloadOption" className="my-3">
        <Form.Label>Download Option</Form.Label>
        <Form.Control
          as="select"
          value={downloadOption}
          onChange={handleDownloadOptionChange}
        >
          <option value="">Select Option</option>
          <option value="quarterly">Quarterly</option>
          <option value="monthly">Monthly</option>
          <option value="yearly">Yearly</option>
        </Form.Control>
      </Form.Group>

      {downloadOption === "quarterly" && (
        <Form.Group controlId="formQuarter">
          <Form.Label>Quarter</Form.Label>
          <Form.Control
            as="select"
            value={quarter}
            onChange={(event) => setQuarter(event.target.value)}
          >
            <option value="">Select Quarter</option>
            <option value="1">Q1</option>
            <option value="2">Q2</option>
            <option value="3">Q3</option>
            <option value="4">Q4</option>
          </Form.Control>
        </Form.Group>
      )}

      {downloadOption === "monthly" && (
        <Form.Group controlId="formMonth">
          <Form.Label>Month</Form.Label>
          <Form.Control
            as="select"
            value={month}
            onChange={(event) => setMonth(event.target.value)}
          >
            <option value="">Select Month</option>
            <option value="1">January</option>
            <option value="2">February</option>
            <option value="3">March</option>
            <option value="4">April</option>
            <option value="5">May</option>
            <option value="6">June</option>
            <option value="7">July</option>
            <option value="8">August</option>
            <option value="9">September</option>
            <option value="10">October</option>
            <option value="11">November</option>
            <option value="12">December</option>
          </Form.Control>
       
          </Form.Group>
  )}

  {downloadOption === "yearly" && (
    <Form.Group controlId="formYear">
      <Form.Label>Year</Form.Label>
      <Form.Control
        type="number"
        placeholder="Enter Year"
        value={year}
        onChange={(event) => setYear(event.target.value)}
      />
    </Form.Group>
  )}

  <Button
  className="my-3"
    variant="primary"
    onClick={() => {
      if (downloadOption === "quarterly") {
        handleQuarterlyDownload();
      } else if (downloadOption === "monthly") {
        handleMonthlyDownload();
      } else if (downloadOption === "yearly") {
        handleYearlyDownload();
      } else {
        alert("Please select a download option");
      }
    }}
  >
    Download Report
  </Button>
</>
);
}

export default DownloadReport;


