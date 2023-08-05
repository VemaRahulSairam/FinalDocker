import React, { useEffect, useState } from "react";
import "./UserList.css";

function UserList() {
  const [users, setUsers] = useState([]);
  const [showPopup, setShowPopup] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:9000/kafkaapp/messages`) 
      .then((response) => response.json())
      .then((data) => setUsers(data))
      .catch((error) => console.error("Error fetching users:", error));
  }, []);

  const handleEmailClick = (user) => {
    setShowPopup(true);
    setSelectedUser(user);
  };

  return (
    <div>
      <h2>User Details</h2>
      <br/>
      {users.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Email</th>
              <th>Password</th>
              <th>Status</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {users.map((user) => (
              <tr key={user.id}>
                <td>{user.email}</td>
                <td>{user.password}</td>
                <td>{user.status}</td>
                <td>
                  <button onClick={() => handleEmailClick(user)}>Send Email</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No users found.</p>
      )}
      {showPopup && (
        <div className="popup">
          <p>Email sent successfully to: {selectedUser.email}</p>
          <button onClick={() => setShowPopup(false)}>Close</button>
        </div>
      )}
    </div>
  );
}

export default UserList;
