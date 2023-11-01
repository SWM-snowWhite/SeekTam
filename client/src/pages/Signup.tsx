import axios from 'axios';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function Signup() {
    const [memberInfo, setMemberInfo] = useState({});
    const [nickname, setNickname] = useState('');
    const [gender, setGender] = useState('');
    const [birthYear, setBirthYear] = useState('');
    const [height, setHeight] = useState('');
    const [weight, setWeight] = useState('');
    const [activityLevel, setActivityLevel] = useState('');
    const [servicePurpose, setServicePurpose] = useState('');
    const navigate = useNavigate()

    const handleNicknameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setNickname(event.target.value);
    };

    const handleGenderChange = (selectedGender: string) => {
    setGender(selectedGender);
    };

    const handleBirthYearChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setBirthYear(event.target.value);
    };

    const handleHeightChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setHeight(event.target.value);
    };

    const handleWeightChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setWeight(event.target.value);
    };

    const handleActivityLevelChange = (selectedActivityLevel: string) => {
    setActivityLevel(selectedActivityLevel);
    };

    const handleServicePurposeChange = (selectedServicePurpose: string) => {
    setServicePurpose(selectedServicePurpose);
    };

    const handleSubmit = () => {
        axios
            .post(`${process.env.REACT_APP_SERVER_API_URL}/member/signup`, 
                {
                    "email": "example@email.com",
                    "applyType": "1",
                    "nickname": "userNickname",
                    "gender": "M",
                    "birthYear": 1990,
                    "status": 1,
                    "height": 175,
                    "weight": 70,
                    "activity": 2,
                    "purposeUse": 3,
                    "lastAccessDate": "2023-10-18T14:30:00"
                }, {
                    withCredentials: true
                }
                )
            .then(res => {
                const retrievedMemberInfo = res.data;
                alert("회원가입에 성공하셨습니다.")
                
                // 회원가입 성공 후 로그인 로직
                setMemberInfo(retrievedMemberInfo);
                login()
                navigate('/main')
            })
            .catch(err => console)
        }

    const login = () => {
        axios
            .post(`${process.env.REACT_APP_SERVER_API_URL}/member/login`, 
                {
                    memberInfo
                })
    }

    return (
    <div className="flex-row align-center w-390 border-1">
        <h1 className="mb-4 text-2xl font-bold">회원가입</h1>
        <div className="mb-4">
        <label className="block text-sm font-medium text-gray-700">닉네임:</label>
        <input
            placeholder='1 ~ 9자리 (공백 입력 불가)'
            type="text"
            className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring focus:ring-blue-300"
            value={nickname}
            onChange={handleNicknameChange}
        />
        </div>
        <div className="mb-4">
        <label className="block text-sm font-medium text-gray-700">성별:</label>
        <div className="flex space-x-2">
            <button
            className={` bg-sub text-[white] px-4 py-2 rounded-md ${
                gender === '여성' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleGenderChange('여성')}
            >
            여성
            </button>
            <button
            className={` bg-sub text-[white] px-4 py-2 rounded-md ${
                gender === '남성' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleGenderChange('남성')}
            >
            남성
            </button>
        </div>
        </div>
        <div className="mb-4">
        <label className="block text-sm font-medium text-gray-700">태어난 연도:</label>
        <input
            type="text"
            className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring focus:ring-blue-300"
            value={birthYear}
            onChange={handleBirthYearChange}
        />
        </div>
        <div className="mb-4">
        <label className="block text-sm font-medium text-gray-700">키 (cm):</label>
        <input
            type="text"
            className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring focus:ring-blue-300"
            value={height}
            onChange={handleHeightChange}
        />
        </div>
        <div className="mb-4">
        <label className="block text-sm font-medium text-gray-700">체중 (kg):</label>
        <input
            type="text"
            className="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring focus:ring-blue-300"
            value={weight}
            onChange={handleWeightChange}
        />
        </div>
        <div className="mb-4">
        <label className="block text-sm font-medium text-gray-700">활동량:</label>
        <div className="flex space-x-2">
            <button
            className={`bg-sub text-[white] px-4 py-2 rounded-md ${
                activityLevel === '낮음' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleActivityLevelChange('낮음')}
            >
            거의 하지않음
            </button>
            <button
            className={`bg-sub text-[white] px-4 py-2 rounded-md ${
                activityLevel === '보통' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleActivityLevelChange('보통')}
            >
            주 1-3회 이상
            </button>
            <button
            className={`bg-sub text-[white] px-4 py-2 rounded-md ${
                activityLevel === '높음' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleActivityLevelChange('높음')}
            >
            주 3-5회 이상
            </button>
            <button
            className={`bg-sub text-[white] px-4 py-2 rounded-md ${
                activityLevel === '매우 높음' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleActivityLevelChange('매우 높음')}
            >
            주 6회 이상
            </button>
        </div>
        </div>
        <div className="mb-4">
        <label className="block text-sm font-medium text-gray-700">서비스 이용목적:</label>
        <div className="flex space-x-2">
            <button
            className={`bg-sub text-[white] px-4 py-2 rounded-md ${
                servicePurpose === '다이어트' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleServicePurposeChange('다이어트')}
            >
            다이어트
            </button>
            <button
            className={`bg-sub text-[white] px-4 py-2 rounded-md ${
                servicePurpose === '체중 유지' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleServicePurposeChange('체중 유지')}
            >
            체중 유지
            </button>
            <button
            className={`bg-sub text-[white] px-4 py-2 rounded-md ${
                servicePurpose === '근육량 증가' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleServicePurposeChange('근육량 증가')}
            >
            근육량 증가
            </button>
            <button
            className={`bg-sub text-[white] px-4 py-2 rounded-md ${
                servicePurpose === '기타' ? 'bg-blue-500 text-white' : 'bg-gray-200 text-gray-700'
            }`}
            onClick={() => handleServicePurposeChange('기타')}
            >
            기타
            </button>
        </div>
        </div>
        <div>
        <button
            className="bg-btn px-6 py-3 w-[50%] text-[white] rounded-md hover:bg-sub-600"
            onClick={handleSubmit}
        >
            회원가입
        </button>
        </div>
    </div>
    );  
};
