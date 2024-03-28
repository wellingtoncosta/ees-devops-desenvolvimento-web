import React from 'react'
import renderer from 'react-test-renderer'

import {UserDetails} from './'

describe("UserDetails tests", () => {
    it("renders correctly when it is loading", () => {
        const domTree = renderer.create(
            <UserDetails
                loading={true}
                users={null}
                error={null}
            />
        ).toJSON();

        expect(domTree).toMatchSnapshot();
    });

    it("renders correctly when the user is found", () => {
        const user = {
            id: 1,
            avatar_url: 'https://avataaars.io/',
            login: 'user1',
            name: 'User 1',
        }

        const domTree = renderer.create(
            <UserDetails
                loading={false}
                user={user}
                error={null}
            />
        ).toJSON();

        expect(domTree).toMatchSnapshot();
    });

    it("renders correctly when the user is not found", async () => {
        const error = {
            message: 'Unable to load users'
        }

        const domTree = renderer.create(
            <UserDetails
                loading={false}
                user={null}
                error={error}
            />
        ).toJSON();


        expect(domTree).toMatchSnapshot();
    });
});
