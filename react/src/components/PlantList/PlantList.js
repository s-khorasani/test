import React from 'react';
import './PlantList.css';

const PlantList = () => {
    const plants = [
        { id: 1, name: 'Monstera Deliciosa', imgSrc: '/path/to/monstera.jpg', description: 'Tropische plant met grote bladeren' },
        { id: 2, name: 'Ficus Lyrata', imgSrc: '/path/to/ficus.jpg', description: 'Populaire kamerplant met grote groene bladeren' },
    ];

    return (
        <div className="plant-list">
            {plants.map(plant => (
                <div key={plant.id} className="plant-card">
                    <img src={plant.imgSrc} alt={plant.name} className="plant-image"/>
                    <h3>{plant.name}</h3>
                    <p>{plant.description}</p>
                </div>
            ))}
        </div>
    );
};

export default PlantList;
