import './Plant.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Plant = () => {
    const [plants, setPlants] = useState([]);
    const [error, setError] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);

        axios.get('http://localhost:8080/api/plants/')
            .then(response => {
                if (Array.isArray(response.data)) {
                    setPlants(response.data);
                } else {
                    // Log unexpected response format
                    console.error('Unexpected response format:', response.data);
                    setError('Unexpected data format received from server');
                }
            })
            .catch(error => {
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.error('Server responded with an error:', error.response.status, error.response.data);
                    setError(`Server error: ${error.response.status}`);
                } else if (error.request) {
                    // The request was made but no response was received
                    console.error('No response received:', error.request);
                    setError('No response from server');
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.error('Error setting up request:', error.message);
                    setError('Error in sending request');
                }
            })
            .finally(() => {
                setIsLoading(false);
            });
    }, []);

    return (
        <div>
            <h2>Plant List</h2>
            {isLoading && <p>Loading...</p>}
            {error && <p>Error: {error}</p>}
            {!isLoading && !error && (
                plants.length > 0 ? (
                    <ul>
                        {plants.map(plant => <li key={plant.id}>{plant.name}</li>)}
                    </ul>
                ) : (
                    <p>No plants found.</p>
                )
            )}
        </div>
    );
};

export default Plant;
