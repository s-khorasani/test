// import './PlantProfile.css';

import React, { useState, useEffect } from 'react';
import axios from 'axios';

const PlantProfile = () => {
    const [plantProfiles, setPlantProfiles] = useState([]);
    const [newPlantProfile, setNewPlantProfile] = useState({ name: '', moisture: 0 });
    const [selectedProfileId, setSelectedProfileId] = useState('');

    useEffect(() => {
        fetchPlantProfiles();
    }, []);

    const fetchPlantProfiles = () => {
        axios.get('http://localhost:8080/api/plant_profiles/')
            .then(response => {
                setPlantProfiles(response.data);
            })
            .catch(error => console.error('Error fetching plant profiles:', error));
    };

    const handleCreate = () => {
        axios.post('http://localhost:8080/api/plant_profiles/', newPlantProfile)
            .then(() => {
                fetchPlantProfiles();
                setNewPlantProfile({ name: '', moisture: 0 });
            })
            .catch(error => console.error('Error creating plant profile:', error));
    };

    const handleUpdate = (id, updatedProfile) => {
        axios.put(`http://localhost:8080/api/plant_profiles/${id}`, updatedProfile)
            .then(() => {
                fetchPlantProfiles();
            })
            .catch(error => console.error('Error updating plant profile:', error));
    };

    const handleDelete = id => {
        axios.delete(`http://localhost:8080/api/plant_profiles/${id}`)
            .then(() => {
                fetchPlantProfiles();
            })
            .catch(error => console.error('Error deleting plant profile:', error));
    };

    return (
        <div>
            <h2>Plant Profiles</h2>
            <div>
                <input
                    type="text"
                    placeholder="Name"
                    value={newPlantProfile.name}
                    onChange={e => setNewPlantProfile({ ...newPlantProfile, name: e.target.value })}
                />
                <input
                    type="number"
                    placeholder="Moisture"
                    value={newPlantProfile.moisture}
                    onChange={e => setNewPlantProfile({ ...newPlantProfile, moisture: e.target.value })}
                />
                <button onClick={handleCreate}>Create New Profile</button>
            </div>

            <div>
                {plantProfiles.map(profile => (
                    <div key={profile.id}>
                        <input
                            type="radio"
                            name="selectedProfile"
                            value={profile.id}
                            onChange={() => setSelectedProfileId(profile.id)}
                        />
                        {profile.name} - Moisture: {profile.moisture}
                        <button onClick={() => handleDelete(profile.id)}>Delete</button>
                    </div>
                ))}
            </div>

            {selectedProfileId && (
                <div>
                    <h3>Edit Plant Profile</h3>
                    <input
                        type="text"
                        placeholder="Name"
                        onChange={e => handleUpdate(selectedProfileId, { ...newPlantProfile, name: e.target.value })}
                    />
                    <input
                        type="number"
                        placeholder="Moisture"
                        onChange={e => handleUpdate(selectedProfileId, { ...newPlantProfile, moisture: e.target.value })}
                    />
                    <button onClick={() => handleUpdate(selectedProfileId, newPlantProfile)}>Update Profile</button>
                </div>
            )}
        </div>
    );
};

export default PlantProfile;
