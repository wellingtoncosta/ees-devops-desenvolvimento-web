import React from 'react'
import renderer from 'react-test-renderer'

import {UsersList} from './'

describe("UsersList tests", () => {
    it("renders correctly when it is loading", () => {
        const domTree = renderer.create(
            <UsersList
                loading={true}
                users={[]}
                error={null}
            />
        ).toJSON();

        expect(domTree).toMatchSnapshot();
    });

    it("renders correctly when there are no users", () => {
        const users = []

        const domTree = renderer.create(
            <UsersList
                loading={false}
                users={users}
                error={null}
            />
        ).toJSON();

        expect(domTree).toMatchSnapshot();
    });

    it("renders correctly when there is an user", async () => {
        const users = [
            {
                id: 1,
                avatar_url: 'https://avataaars.io/',
                login: 'user1',
                name: 'User 1',
            }
        ]

        const domTree = renderer.create(
            <UsersList
                loading={false}
                users={users}
                error={null}
            />
        ).toJSON();

        expect(domTree).toMatchSnapshot();
    });

    it("renders correctly when there is an error", async () => {
        const error = {
            message: 'Unable to load users'
        }

        const domTree = renderer.create(
            <UsersList
                loading={false}
                users={[]}
                error={error}
            />
        ).toJSON();


        expect(domTree).toMatchSnapshot();
    });
});
