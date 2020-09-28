import React, {useState} from 'react';

function AddTodo({onAdd}) {

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    const onSubmit = (event) => {
        event.preventDefault();
        onAdd(name, description);
        setName('');
        setDescription('')
    };

    const onChangeName = (event) => setName(event.target.value);
    const onChangeDescription = (event) => setDescription(event.target.value);

    return (
        <form onSubmit={onSubmit}>
            <input type="text" placeholder="Name" value={name} onChange={onChangeName}/>
            {`: `}
            <input type="text" placeholder="Description" value={description} onChange={onChangeDescription}/>
            {` ( `}
            <button>Add</button>
            {` )`}
        </form>
    )
}

export default AddTodo