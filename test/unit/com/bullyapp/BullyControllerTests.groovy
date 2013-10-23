package com.bullyapp



import org.junit.*
import grails.test.mixin.*

@TestFor(BullyController)
@Mock(Bully)
class BullyControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/bully/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bullyInstanceList.size() == 0
        assert model.bullyInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.bullyInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bullyInstance != null
        assert view == '/bully/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/bully/show/1'
        assert controller.flash.message != null
        assert Bully.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bully/list'

        populateValidParams(params)
        def bully = new Bully(params)

        assert bully.save() != null

        params.id = bully.id

        def model = controller.show()

        assert model.bullyInstance == bully
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bully/list'

        populateValidParams(params)
        def bully = new Bully(params)

        assert bully.save() != null

        params.id = bully.id

        def model = controller.edit()

        assert model.bullyInstance == bully
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bully/list'

        response.reset()

        populateValidParams(params)
        def bully = new Bully(params)

        assert bully.save() != null

        // test invalid parameters in update
        params.id = bully.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/bully/edit"
        assert model.bullyInstance != null

        bully.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bully/show/$bully.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bully.clearErrors()

        populateValidParams(params)
        params.id = bully.id
        params.version = -1
        controller.update()

        assert view == "/bully/edit"
        assert model.bullyInstance != null
        assert model.bullyInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bully/list'

        response.reset()

        populateValidParams(params)
        def bully = new Bully(params)

        assert bully.save() != null
        assert Bully.count() == 1

        params.id = bully.id

        controller.delete()

        assert Bully.count() == 0
        assert Bully.get(bully.id) == null
        assert response.redirectedUrl == '/bully/list'
    }
}
