package es.securitasdirect.tareas.web.controller.task.exceltask;

import es.securitasdirect.tareas.model.InstallationData;
import es.securitasdirect.tareas.model.tareaexcel.MaintenanceSurveyTask;
import es.securitasdirect.tareas.service.InstallationService;
import es.securitasdirect.tareas.service.QueryTareaService;
import es.securitasdirect.tareas.web.controller.TaskController;
import es.securitasdirect.tareas.web.controller.dto.TareaResponse;
import es.securitasdirect.tareas.web.controller.dto.request.exceltask.maintenancesurvey.DiscardMaintenanceSurveyTaskRequest;
import es.securitasdirect.tareas.web.controller.dto.request.exceltask.maintenancesurvey.FinalizeMaintenanceSurveyTaskRequest;
import es.securitasdirect.tareas.web.controller.dto.request.exceltask.maintenancesurvey.PostponeMaintenanceSurveyTaskRequest;
import es.securitasdirect.tareas.web.controller.dto.support.BaseResponse;
import es.securitasdirect.tareas.web.controller.dto.support.DummyResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * @author
 */

@Controller
@RequestMapping("/maintenancesurveytask")
public class MaintenanceSurveyTaskController extends TaskController {

    @Inject
    private QueryTareaService queryTareaService;
    @Inject
    private InstallationService installationDataService;
    @Inject
    private DummyResponseGenerator dummyResponseGenerator;

    private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceSurveyTaskController.class);

    @RequestMapping(value = "/gettarea", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody TareaResponse getMaintenanceSurveyTask(
        @RequestParam(value = "ccUserId", required = true) String ccUserId,
        @RequestParam(value = "callingList", required = true) String callingList,
        @RequestParam(value = "tareaId", required = true) String tareaId
    )  {
        String TASK_SERVICE_MESSAGE = "maintenanceSurveyTask.getTask";
        TareaResponse response;
        LOGGER.debug("Get maintenance survey task for params: \nccUserId:{}\ncallingList:{}\ntareaId:{}", ccUserId, callingList, tareaId);
        try{
            MaintenanceSurveyTask tarea = (MaintenanceSurveyTask) queryTareaService.queryTarea(ccUserId, callingList, tareaId);
            LOGGER.debug("Maintenance survey task obtained from service: \n{}", tarea);
            response = processSuccessTask(tarea,TASK_SERVICE_MESSAGE );
        }catch(Exception e){
            response = processException(e,TASK_SERVICE_MESSAGE);
        }
        return response;
    }


    @RequestMapping(value = "/getInstallationAndTask", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody TareaResponse getInstallationAndTask(
            @RequestParam(value = "installationId", required = true) String installationId, //TODO QUITAR ESTE PARAMETRO
            @RequestParam(value = "ccUserId", required = true) String ccUserId,
            @RequestParam(value = "callingList", required = true) String callingList,
            @RequestParam(value = "tareaId", required = true) String tareaId
    ) {

        LOGGER.debug("Get Notification task for params: \nccUserId:{}\ncallingList:{}\ntareaId:{}",ccUserId, callingList, tareaId);
        TareaResponse response = new TareaResponse();
        try {
            //Buscar Tarea
            MaintenanceSurveyTask task = (MaintenanceSurveyTask)queryTareaService.queryTarea(ccUserId, callingList, tareaId);
            if (task!=null) {
                response.setTarea(task);
                //Buscamos la instalación
                if (task.getNumeroInstalacion()!=null) {
                    InstallationData installationData = installationDataService.getInstallationData(task.getNumeroInstalacion());
                    if (installationData!=null) {
                        response.setInstallationData(installationData);
                    } else {
                        response.danger("getTask.noInstallation");
                    }
                } else {
                    response.danger("getTask.noInstallation");
                }
            } else {
                response.danger("getTask.notFound");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            processException(e);
        }
        return response;


        /*
        String TASK_SERVICE_MESSAGE = "maintenanceSurveyTask.getTask";
        TareaResponse response = new TareaResponse();
        LOGGER.debug("Get maintenance survey task for params: \nccUserId:{}\ncallingList:{}\ntareaId:{}", ccUserId, callingList, tareaId);
        try{
            MaintenanceSurveyTask tarea = (MaintenanceSurveyTask) queryTareaService.queryTarea(ccUserId, callingList, tareaId);
            TareaResponse tareaResponse = processSuccessTask(tarea, TASK_SERVICE_MESSAGE);
            response.addMessages(tareaResponse.getMessages());
            response.setTarea(tareaResponse.getTarea());
            LOGGER.debug("Maintenance survey task obtained from service: \n{}", tarea);
        }catch(Exception e){
            TareaResponse tareaResponse = processException(e, TASK_SERVICE_MESSAGE);
            response.addMessages(tareaResponse.getMessages());
        }
        String INSTALLATION_SERVICE_MESSAGE = "installationData";
        LOGGER.debug("Get installation data for params: \ninstallationId: {}", installationId);
        try{
            InstallationData installationData = installationDataService.getInstallationData(installationId);
            TareaResponse installationResponse = processSuccessInstallation(installationData);
            response.addMessages(installationResponse.getMessages());
            response.setInstallationData(installationResponse.getInstallationData());
            LOGGER.debug("Installation data obtained from service: \n{}", installationData);
        }catch(Exception e){
            TareaResponse installationResponse = processException(e, INSTALLATION_SERVICE_MESSAGE);
            response.addMessages(installationResponse.getMessages());
        }
        return response;
        */
    }


    @RequestMapping(value = "/aplazar", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody BaseResponse aplazar(@RequestBody PostponeMaintenanceSurveyTaskRequest request) {
        LOGGER.debug("Aplazar tarea de encuesta de mantenimiento:\nRequest: {}", request);
        BaseResponse response = dummyResponseGenerator.dummyFinalizeSuccess();
        LOGGER.debug("Aplazada tarea de encuesta de mantenimiento:\nResponse: {}",response);
        return response;
    }


    @RequestMapping(value = "/descartar", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody BaseResponse descartar(@RequestBody DiscardMaintenanceSurveyTaskRequest request) {
        LOGGER.debug("Descartar tarea de encuesta de mantenimiento:\nRequest: {}", request);
        BaseResponse response = dummyResponseGenerator.dummyCustomSuccess("commonexcel.discard.success");
        LOGGER.debug("Descartada tarea de encuesta de mantenimiento:\nResponse: {}",response);
        return response;
    }

    @RequestMapping(value = "/finalizar", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody BaseResponse finalizar(@RequestBody FinalizeMaintenanceSurveyTaskRequest request) {
        LOGGER.debug("Finalizar tarea de encuesta de mantenimiento:\nRequest: {}", request);
        BaseResponse response = dummyResponseGenerator.dummyCustomSuccess("commonexcel.finalize.success");
        LOGGER.debug("Finalizada tarea de encuesta de mantenimiento:\nResponse: {}",response);
        return response;
    }


}
