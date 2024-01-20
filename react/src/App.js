import React, { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';
import Dashboard from "./components/Dashboard/Dashboard";

const App = () => {
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/test');
                setData(response.data);
            } catch (err) {
                console.error(err);
                setError('Er is een fout opgetreden bij het ophalen van de gegevens.');
            }
        };

        fetchData();
    }, []);
    return (
        <div className="App">
            <header className="App-header">
                {data ? (
                    <p>{data.message}</p>
                ) : error ? (
                    <p style={{ color: 'red' }}>{error}</p>
                ) : (
                    <p>Loading...</p>
                )}
            </header>
            <Dashboard />
        </div>

    );
};

export default App;
