import './Container.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Container = () => {
    const [containers, setContainers] = useState([]);
    const [error, setError] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        setIsLoading(true);

        axios.get('http://localhost:8080/api/containers/')
            .then(response => {
                if (Array.isArray(response.data)) {
                    setContainers(response.data);
                } else {
                    console.error('Unexpected response format:', response.data);
                    setError('Unexpected data format received from server');
                }
            })
            .catch(error => {
                if (error.response) {
                    console.error('Server responded with an error:', error.response.status, error.response.data);
                    setError(`Server error: ${error.response.status}`);
                } else if (error.request) {
                    console.error('No response received:', error.request);
                    setError('No response from server');
                } else {
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
            <h2>Container List</h2>
            {isLoading && <p>Loading...</p>}
            {error && <p>Error: {error}</p>}
            {!isLoading && !error && (
                containers.length > 0 ? (
                    <ul>
                        {containers.map(container => (
                            <li key={container.id}>
                                Capacity: {container.capacity}, Quantity: {container.quantity}
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>No containers found.</p>
                )
            )}
        </div>
    );
};

export default Container;
