import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import Avatar from "react-avatar";

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
import AuthService from "../services/auth.service";

const Profile = () => {
  const user = AuthService.getCurrentUser();
  const [content, setContent] = useState("");
  const [fullName, setFullName] = useState("");
  const [interest, setInterest] = useState("");
  const [occupation, setOccupation] = useState("");
  const [contacts, setContacts] = useState("");
  const [city, setCity] = useState("");
  const [country, setCountry] = useState("");
  const [presentation, setPresentation] = useState("");
  const contentSkills = content.skills;

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
  }, [content]);

  const nameData = {
    editCase: 1,
    fullName: fullName,
    userId: user.id,
  };
  const interestData = {
    editCase: 2,
    interest: interest,
    userId: user.id,
  };
  const occupationData = {
    editCase: 6,
    occupation: occupation,
    userId: user.id,
  };
  const contactsData = {
    editCase: 5,
    contacts: contacts,
    userId: user.id,
  };
  const cityData = {
    editCase: 4,
    city: city,
    userId: user.id,
  };
  const countryData = {
    editCase: 3,
    country: country,
    userId: user.id,
  };
  const presentationData = {
    editCase: 7,
    presentation: presentation,
    userId: user.id,
  };

  const validate = (value) => {
    if (value.length < 3 || value.length > 20) {
      return (
        <div className="alert alert-danger" role="alert">
          This field must be between 3 and 20 characters.
        </div>
      );
    }
  };
  const validatePresentation = (value) => {
    if (value.length < 1 || value.length > 200) {
      return (
        <div className="alert alert-danger" role="alert">
          This field must be between 1 and 200 characters.
        </div>
      );
    }
  };

  const onChangeFullName = (e) => {
    const fullName = e.target.value;
    setFullName(fullName);
  };
  const onChangeInterest = (e) => {
    const interest = e.target.value;
    setInterest(interest);
  };
  const onChangeOccupation = (e) => {
    const occupation = e.target.value;
    setOccupation(occupation);
  };
  const onChangeCity = (e) => {
    const city = e.target.value;
    setCity(city);
  };
  const onChangeContacts = (e) => {
    const contacts = e.target.value;
    setContacts(contacts);
  };
  const onChangeCountry = (e) => {
    const country = e.target.value;
    setCountry(country);
  };
  const onChangePresentation = (e) => {
    const presentation = e.target.value;
    setPresentation(presentation);
  };

  const handleUpdate = (e) => {
    e.preventDefault();

    if (fullName !== "") {
      UserService.editUser(nameData);
    }
    if (interest !== "") {
      UserService.editUser(interestData);
    }
    if (occupation !== "") {
      UserService.editUser(occupationData);
    }
    if (city !== "") {
      UserService.editUser(cityData);
    }
    if (country !== "") {
      UserService.editUser(countryData);
    }
    if (contacts !== "") {
      UserService.editUser(contactsData);
    }
    if (presentation !== "") {
      UserService.editUser(presentationData);
    }
  };

  const [skillData, setSkillData] = useState({});

  const javaData = {
    skillId: 1,
    userId: user.id,
  };
  const jsData = {
    skillId: 2,
    userId: user.id,
  };
  const pyData = {
    skillId: 3,
    userId: user.id,
  };
  const htmlcssData = {
    skillId: 4,
    userId: user.id,
  };
  const csData = {
    skillId: 5,
    userId: user.id,
  };
  const sqlData = {
    skillId: 6,
    userId: user.id,
  };

  const [toggle, setToggled] = useState(false);

  const toggler = () => {
    toggle ? setToggled(false) : setToggled(true);
  };

  useEffect(() => {
    setToggled(JSON.parse(window.localStorage.getItem("toggle")));
  }, []);

  useEffect(() => {
    window.localStorage.setItem("toggle", toggle);
  }, [toggle]);

  useEffect(() => {
    if (toggle) {
      UserService.addSkill(skillData);
    }
    if (!toggle) {
      UserService.deleteSkill(skillData);
    }
  }, [toggle]);

  return (
    <>
      <Container className="profile-container" fluid>
        <Row>
          <Col md="8">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Edit information</Card.Title>
              </Card.Header>
              <Card.Body>
                <Form>
                  <Row>
                    <Col className="pr-1" md="5">
                      <Form.Group>
                        <label>Full name</label>
                        <Form.Control
                          placeholder={content.fullName}
                          type="text"
                          value={fullName}
                          onChange={onChangeFullName}
                          validations={[validate]}
                        ></Form.Control>
                      </Form.Group>
                    </Col>

                    <Col className="px-1" md="3">
                      <Form.Group>
                        <label>Occupation</label>
                        <Form.Control
                          placeholder={content.occupation}
                          type="text"
                          value={occupation}
                          onChange={onChangeOccupation}
                          validations={[validate]}
                        ></Form.Control>
                      </Form.Group>
                    </Col>

                    <Col className="pl-1" md="4">
                      <Form.Group>
                        <label>Interest</label>
                        <Form.Control
                          placeholder={content.interests}
                          type="text"
                          value={interest}
                          onChange={onChangeInterest}
                          validations={[validate]}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>

                  <Row>
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>Skills</label>{" "}
                        <Button
                          onClick={() => [toggler(), setSkillData(javaData)]}
                        >
                          Java
                        </Button>{" "}
                        <Button
                          onClick={() => [toggler(), setSkillData(jsData)]}
                        >
                          JavaScript
                        </Button>{" "}
                        <Button
                          onClick={() => [toggler(), setSkillData(pyData)]}
                        >
                          Python
                        </Button>{" "}
                        <Button
                          onClick={() => [toggler(), setSkillData(htmlcssData)]}
                        >
                          HTML/CSS
                        </Button>{" "}
                        <Button
                          onClick={() => [toggler(), setSkillData(csData)]}
                        >
                          C#
                        </Button>{" "}
                        <Button
                          onClick={() => [toggler(), setSkillData(sqlData)]}
                        >
                          SQL
                        </Button>
                      </Form.Group>
                    </Col>

                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label>Contacts</label>
                        <Form.Control
                          placeholder={content.contacts}
                          type="text"
                          value={contacts}
                          onChange={onChangeContacts}
                          validations={[validate]}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>

                  <Row>
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>City</label>
                        <Form.Control
                          placeholder={content.city}
                          type="text"
                          value={city}
                          onChange={onChangeCity}
                          validations={[validate]}
                        ></Form.Control>
                      </Form.Group>
                    </Col>

                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label>Country</label>
                        <Form.Control
                          placeholder={content.country}
                          type="text"
                          value={country}
                          onChange={onChangeCountry}
                          validations={validate}
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
                          placeholder={content.presentation}
                          rows="4"
                          as="textarea"
                          value={presentation}
                          onChange={onChangePresentation}
                          validations={validatePresentation}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>

                  <Button
                    className="btn-fill pull-right"
                    type="submit"
                    variant="info"
                    onClick={handleUpdate}
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
                <div className="profile-avatar">
                  <Avatar name={content.fullName} size="120" round={true} />{" "}
                </div>

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
                  Country: <span>{content.country}</span>
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
