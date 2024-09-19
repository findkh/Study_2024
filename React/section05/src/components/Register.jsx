import { useState } from 'react'

const Register = () => {

    const [name, setName] = useState('이름');
    const [birth, setBirth] = useState('');
    const [country, setCountry] = useState('');
    const [bio, setBio] = useState('');

    const onChangeName = (e) => {
        setName(e.target.value);        
    }

    const onChangeBirth = (e) => {
         setBirth(e.target.value)
    }

    const onChangeCountry = (e) => {
        setCountry(e.target.value)
    }

    const onChangeBio = (e) => {
        setBio(e.target.value)
    }

    return (
        <div>
            <div>
                <input 
                    value={name}
                    onChange={onChangeName} 
                    placeholder="이름" 
                    type="text"/>
            </div>
            <div>
                <input type="date"
                    value={birth}
                    onChange={onChangeBirth} />
            </div>
            <div>
                <select onChange={onChangeCountry} value={country}>
                    <option value=""></option>
                    <option value="kr">한국</option>
                    <option value="us">미국</option>
                    <option value="uk">영국</option>
                </select>
                {country}
            </div>
            <div>
                <textarea onChange={onChangeBio} value={bio} />
                {bio}
            </div>
        </div>
    )
}

export default Register