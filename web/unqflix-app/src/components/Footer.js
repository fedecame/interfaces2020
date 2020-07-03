import React from 'react';
import helado from '../images/helado.png';
import '../styles/login.scss';

const Footer = () => {
    return (
        <footer id="footerWrapper">
            <div id="firmaFooter">
                <img id="imagenHelado"	src={helado} alt="Helado Napolitano"/>
                <span>Copyrigth 2020 - Grupo Helado Napolitano</span>
            </div>
            {/* <span>
                Iconos diseñados por <a href="http://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.es/" title="Flaticon"> www.flaticon.es</a> 
            </span> */}
        </footer>
    );
}

export default Footer;