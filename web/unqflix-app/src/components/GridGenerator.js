import React from 'react';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

const GridGenerator = ({colAmount, children}) => {
    // colAmount tiene que ser multiplo de 12, sino se redondea para abajo la cantidad de columnas
    const colWidth = Math.floor(12 / colAmount);

    const chunk = (arr, chunkSize = 1, cache = []) => {
        const tmp = [...arr]
        if (chunkSize <= 0) return cache
        while (tmp.length) cache.push(tmp.splice(0, chunkSize))
        return cache
    }

    const rows = chunk(children, colAmount);

    return (
        <Container fluid className="bg-dark pt-5">
            {rows.map((cols, index) => (
                <Row key={index}>
                    {cols.map((col, index) => (
                        <Col xs={12} sm={colWidth <= 2 ? colWidth*3 : colWidth} lg={colWidth <= 3 ? colWidth*2 : colWidth} xl={colWidth} key={index}>
                            {col}
                        </Col>
                    ))}
                </Row>
            ))}
        </Container>
    );
}
 
export default GridGenerator;