import React, { useState, useEffect } from 'react';
import helado from '../images/helado.png';
import '../styles/login.scss';

const Footer = (props) => {
    return (
        <footer id="footerWrapper">
            <div id="firmaFooter">
                <img id="imagenHelado"	src={helado} alt="Helado Napolitano"/>
                <span>Copyrigth 2020 - Grupo Helado Napolitano</span>
            </div>
        </footer>
    );
}

export default Footer;