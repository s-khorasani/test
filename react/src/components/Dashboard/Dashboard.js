import React from 'react';
import './Dashboard.css';
import UserPanel from '../UserPanel/UserPanel';
import PlantList from '../PlantList/PlantList';
import PlantProfiles from '../PlantProfiles/PlantProfiles';
import ContainerDetails from '../ContainerDetails/ContainerDetails';
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
                <div className="dashboard-component"><UserPanel /></div>
                <div className="dashboard-component"><PlantList /></div>
                <div className="dashboard-component"><PlantProfiles /></div>
                <div className="dashboard-component"><ContainerDetails /></div>
            </div>
        </div>
    );
};

export default Dashboard;

