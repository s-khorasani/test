import React from 'react';
import './Dashboard.css';
import User from '../UserPanel/User';
import Plant from '../PlantList/Plant';
import PlantProfile from '../PlantProfiles/PlantProfile';
import Container from '../Container/Container';
import logoPlantBuddy from '../../assets/images/Logo Plant Buddy resized.png';

const Sidebar = () => {
    return (
        <div className="sidebar">
            <img src={logoPlantBuddy} alt="Logo Plant Buddy" className="logo" />
            <nav>
                <ul>
                    <li>Home</li>
                    <li>Dashboard</li>
                </ul>
            </nav>
        </div>
    );
};

const Dashboard = () => {
    return (
        <div className="dashboard">
            <Sidebar />
            <div className="main-content">
                <div className="dashboard-component"><User /></div>
                <div className="dashboard-component"><Plant /></div>
                <div className="dashboard-component"><PlantProfile /></div>
                <div className="dashboard-component"><Container /></div>
            </div>
        </div>
    );
};

export default Dashboard;

