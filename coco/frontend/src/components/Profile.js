import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import {
  Badge,
  Button,
  Card,
  Form,
  Navbar,
  Nav,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import "./Profile.css";

const Profile = () => {
  const [content, setContent] = useState("");
  const contentSkills = content.skills;
  let skillJava = 1;

  useEffect(() => {
    UserService.getUserInfo().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setContent(_content);

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }, []);

  const [toggle, setToggled] = useState(false);

  const toggler = () => {
    toggle ? setToggled(false) : setToggled(true);
  };

  const [buttonColor, setButtonColor] = useState("");

  useEffect(() => {
    if (toggle) {
      setButtonColor("btn-blue");
    }
    if (!toggle) {
      setButtonColor("btn-green");
    }
  }, [toggle]);

  return (
    <>
      <Container className="profile-container" fluid>
        <Row>
          <Col md="8">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Add information</Card.Title>
              </Card.Header>
              <Card.Body>
                <Form>
                  <Row>
                    <Col className="pr-1" md="5">
                      <Form.Group>
                        <label>Full name</label>
                        <Form.Control type="text"></Form.Control>
                      </Form.Group>
                    </Col>

                    <Col className="px-1" md="3">
                      <Form.Group>
                        <label>Occupation</label>
                        <Form.Control type="text"></Form.Control>
                      </Form.Group>
                    </Col>

                    <Col className="pl-1" md="4">
                      <Form.Group>
                        <label>Interest</label>
                        <Form.Control type="text"></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>

                  <Row>
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>Skills</label>{" "}
                        <Button
                          onClick={() => {
                            UserService.putUserSkill(skillJava, content.id);
                          }}
                          className={buttonColor}
                        >
                          Java {toggle ? <span>-</span> : <span>+</span>}
                        </Button>{" "}
                        <Button>JavaScript</Button> <Button>Python</Button>{" "}
                        <Button>HTML/CSS</Button> <Button>C#</Button>{" "}
                        <Button>SQL</Button>
                      </Form.Group>
                    </Col>

                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label>Contacts</label>
                        <Form.Control type="text"></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>

                  <Row>
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>City</label>
                        <Form.Control type="text"></Form.Control>
                      </Form.Group>
                    </Col>

                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label>Country</label>
                        <Form.Control
                          defaultValue={"Sweden"}
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>

                  <Row>
                    <Col md="12">
                      <Form.Group>
                        <label>Presentation</label>
                        <Form.Control
                          cols="80"
                          placeholder="Tell more about yourself"
                          rows="4"
                          as="textarea"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>

                  <Button
                    className="btn-fill pull-right"
                    type="submit"
                    variant="info"
                  >
                    Update Profile
                  </Button>
                  <div className="clearfix"></div>
                </Form>
              </Card.Body>
            </Card>
          </Col>
          <Col md="4">
            <Card className="card-user">
              <Card.Body>
                <div className="author">
                  <a href="#pablo" onClick={(e) => e.preventDefault()}>
                    <h1 className="title">{content.fullName}</h1>
                  </a>
                  <h5 className="description">
                    Email : <span>{content.email}</span>
                  </h5>
                </div>
                <h5>
                  Occupation: <span>{content.occupation} </span>
                </h5>
                <h5>
                  Interest: <span>{content.interests}</span>
                </h5>
                <h5>
                  Skills:{" "}
                  {contentSkills?.map((skill) => (
                    <span>
                      [{skill.name}]<span></span>{" "}
                    </span>
                  ))}
                </h5>
                <h5>
                  Contacts: <span>{content.contacts}</span>
                </h5>
                <h5>
                  City: <span>{content.city} </span>
                </h5>
                <h5>
                  Country: <span>...</span>
                </h5>

                <hr></hr>

                <h6 className="description text-center">
                  {content.presentation}
                </h6>
              </Card.Body>
              <hr></hr>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Profile;
