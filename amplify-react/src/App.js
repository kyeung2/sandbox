import React from 'react';
import logo from './logo.svg';
import './css/App.css';

import Amplify from 'aws-amplify';

import awsconfig from './aws-exports';
import {withAuthenticator} from 'aws-amplify-react'; // or 'aws-amplify-react-native';
import '@aws-amplify/ui/dist/style.css';
import TodoList from "./components/TodoList";
Amplify.configure(awsconfig);


function App() {
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <TodoList/>
            </header>
        </div>
    );
}

const signUpConfig = {
    header: 'Create a new account',
    hideAllDefaults: true,
    defaultCountryCode: '44',
};

export default withAuthenticator(App,
    true,
    [],
    null,
    null,
    {signUpConfig});
