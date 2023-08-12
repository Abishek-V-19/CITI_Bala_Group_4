import React from "react";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div>
      <div>
        <div>
          <Link to="/transactions">
            <button>Transactions</button>
          </Link>
        </div>
        <div>
          <Link to="/add">
            <button>Add credit card</button>
          </Link>
        </div>
        <div>
          <Link to="/delete">
            <button>Delete credit card</button>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Home;
