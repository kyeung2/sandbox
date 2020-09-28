import React, {useState} from 'react';

function EditProduct({id, originalName, originalDescription, onEditSubmit}) {

    const [name, setName] = useState('');
    const [description, setDescription] = useState(0);

    const onSubmit = (event) => {
        event.preventDefault();
        onEditSubmit(id, name || originalName, description || originalDescription)
    };

    const onChangeName = (event) => setName(event.target.value);
    const onChangeDescription = (event) => setDescription(event.target.value);

    return (
        <form onSubmit={onSubmit}>
            <input type="text" placeholder="Name" defaultValue={originalName} onChange={onChangeName}/>
            {`: `}
            <input type="text" placeholder="Description" defaultValue={originalDescription} onChange={onChangeDescription}/>
            {` ( `}
            <button>Save</button>
            {` )`}
        </form>
    )
}

export default EditProduct