import React from 'react';
import { FiArrowLeftCircle } from 'react-icons/fi';
import { Link, useNavigate } from 'react-router-dom';

export default function Navigator() {
    const navigate = useNavigate()

    const handleClick = () => {
        navigate(-1)
    }
    return (
        <div className='flex justify-center w-full p-0 m-0 h-100 bg-main'>
            {/* <button className=''>
                <FiArrowLeftCircle size={26} className='text-main' onClick={handleClick}/>
            </button> */}
            <Link to='/'>
                <button className='mt-30'>
                    <img className='w-40' src="./images/logo_typeface.png"/>
                </button>
            </Link>
            <div className='ml-10'></div>
        </div>
    );
}

