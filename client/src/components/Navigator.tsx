import React from 'react';
import { PiMagnifyingGlass } from 'react-icons/pi';
import { FiArrowLeftCircle } from 'react-icons/fi';
import { Link, useNavigate } from 'react-router-dom';

export default function Navigator() {
    const navigate = useNavigate()

    const handleClick = () => {
        navigate(-1)
    }

    return (
        <div className='flex justify-between mx-20 mt-15'>
            <button>
                <FiArrowLeftCircle size={26} className='text-main' onClick={handleClick}/>
            </button>
            <Link to='/'>
                <button>
                    <span className='font-bold text-main text-20'>식탐</span>
                </button>
            </Link>
            <Link to='/keyword'>
                <button>
                    <PiMagnifyingGlass size={26} className='text-main'/>
                </button>
            </Link>
            
        </div>
    );
}

