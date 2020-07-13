import React from 'react';
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import '../styles/seasons-dropdowns.scss';

const SeasonsDropdowns = ({seasons, setChapterVideo, setShowVideo}) => {

    const showVideo = (event, video) => {
        event.preventDefault();
        setChapterVideo(video);
        setShowVideo(true);
    };

    return ( 
        <>
        {seasons.map(
            ({chapters, description, id, poster, title}) => (
            <DropdownButton
                as={ButtonGroup}
                key={id}
                id={id}
                variant='light'
                size='lg'
                title={title}
                className="seasons-button"
            >
                {chapters.map(
                    ({description, duration, id, thumbnail, title, video}, index) => (
                        <Dropdown.Item key={id} eventKey={id} onClick={event => showVideo(event, video)}>{title}</Dropdown.Item>
                    )
                )}
            </DropdownButton>
            )
        )}
        </>
    );
}
 
export default SeasonsDropdowns;