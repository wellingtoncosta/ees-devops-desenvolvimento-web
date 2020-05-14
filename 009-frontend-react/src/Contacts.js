import React from 'react';

const data = [
  {
    "_id": "5ebc6ad49e9e14de27a178da",
    "name": "Jacqueline Malone",
    "email": "jacquelinemalone@roughies.com",
    "phone": "+1 (813) 506-3229"
  },
  {
    "_id": "5ebc6ad4e81a79876f5f6a26",
    "name": "Cortez Lindsey",
    "email": "cortezlindsey@roughies.com",
    "phone": "+1 (978) 487-3007"
  },
  {
    "_id": "5ebc6ad41a13a86f590101f5",
    "name": "Klein Summers",
    "email": "kleinsummers@roughies.com",
    "phone": "+1 (924) 450-2281"
  },
  {
    "_id": "5ebc6ad423431ad942dd95c9",
    "name": "Walton Ellison",
    "email": "waltonellison@roughies.com",
    "phone": "+1 (954) 570-2425"
  },
  {
    "_id": "5ebc6ad4238d6de0ae859f24",
    "name": "Chandler Sharp",
    "email": "chandlersharp@roughies.com",
    "phone": "+1 (870) 426-2546"
  },
  {
    "_id": "5ebc6ad420b1f3e1c7b9a173",
    "name": "Nola Fowler",
    "email": "nolafowler@roughies.com",
    "phone": "+1 (911) 595-3234"
  },
  {
    "_id": "5ebc6ad488e957d86a9305e7",
    "name": "April Cummings",
    "email": "aprilcummings@roughies.com",
    "phone": "+1 (845) 410-2694"
  }
];

class ContactItem extends React.Component {
  render() {
    const contact = this.props.contact;
    return (
      <tr>
        <td>{contact.name}</td>
        <td>{contact.email}</td>
        <td>{contact.phone}</td>
      </tr>
    );
  }
}

class ContactsTable extends React.Component {
  render() {
    const contacts = this.props.contacts;
    return (
      <table border="1px">
        <thead>
        <tr>
          <th>Name</th>
          <th>E-mail</th>
          <th>Phone</th>
        </tr>
        </thead>
        <tbody>
          {
            contacts.map(contact => <ContactItem key={contact._id} contact={contact}  />)
          }
        </tbody>
      </table>
    )
  }
}

class Contacts extends React.Component {
  render() {
    return (
      <div align="center">
        <h2>
          Contacts
        </h2>
        <br />
        <ContactsTable contacts={data} />
      </div>
    );
  }
};

export default Contacts;
